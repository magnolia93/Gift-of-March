import axios from 'axios';
import React, { useCallback, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import TreesContainer from '../Containers/TreesContainer';
import tree, { blossom, init, select, update } from '../modules/treeStore';
import { Link } from 'react-router-dom';
import './Home.css';

// 버튼을 눌러 나무 생성, 받아오기, 등을 axios로 요청
// 받아온 데이터를 리듀서로 디스패치

const Home = () => {

    const [num, SetNum] = useState('');
    const onNumChange = (e) => {
        SetNum(e.target.value);
    }

    // 리덕스 스토어 참조 변수
    const trees = useSelector(state => state.treeStore.trees);
    const bundle = useSelector(state => state.treeStore.bundle);

    // 디스패치 함수들
    const dispatch = useDispatch();
    const onInit = useCallback((payload) => dispatch(init(payload)), [dispatch]);
    const onUpdate = useCallback(( payload ) => dispatch(update(payload)), [dispatch]);
    const onBlossom = useCallback(( payload ) => dispatch(blossom(payload)), [dispatch]);
    const onSelect = useCallback(( payload ) => dispatch(select(payload)), [dispatch]);
    const onMake = () => {
        // bundle에는 꽃이 들어 있다.
        const phrases = bundle.map((o) => ({id: o.id, content: o.phrase}));

        const startTime = new Date();
        axios.post("http://localhost:8080/march/bundle/tempTitle", phrases).then(response => {
            const endTime = new Date();
            console.log("process time: " + (endTime - startTime) + "ms");
        });
        const initTrees = trees.map(tree => {
            const flowers = tree.flowerList.map(flower => ({...flower, checked: false, sent: false}));
            return {...tree, flowerList: flowers};
        });
        onInit(initTrees);
    }

    const onClickPlant = () => {
        axios.get(`http://localhost:8080/march/tree/${num}`).then(response => {
            console.log(`${response.data}개의 나무가 성공적으로 심어졌습니다`);
        });
    };

    const onClickBlossom = () => {
        const startTime = new Date();
        axios.get(`http://localhost:8080/march/flower/blossom`).then(response => {
            const endTime = new Date();
            console.log("Process Time: " + (endTime - startTime) + "ms");
            const treesFromBack = response.data.map(tree => {
                if (tree.flowerList === null) return tree;

                const flowers = tree.flowerList.map(flower => ({...flower, checked: false, sent: false}));
                return {...tree, flowerList: flowers};
            });
            onBlossom(treesFromBack);
        });
    };

    const onClickFlower = (treeId, flowerId) => {
        const targetTree = trees.find(tree => tree.id === treeId);
        const targetFlower = targetTree.flowerList.find(flower => flower.id === flowerId);

        const nextTrees = trees.map(tree => {
            if (tree.id === treeId) {
                const newFlowerList = tree.flowerList.map(flower => {
                    // 체크되어 있는 꽃을 해제하거나 체크되어 있지 않은 꽃을 선택했는데 아직 bundle 제한을 넘지 않은 경우에는
                    // 꽃의 체크를 토글한다
                    if (flower.id === flowerId && flower.checked) {
                        return {...flower, checked: !flower.checked};
                    }
                    else if (flower.id === flowerId && !flower.checked && bundle.length < 3){
                        return {...flower, checked: !flower.checked};
                    }

                    // 위 상황 외에는 꽃을 토글하면 안된다.
                    return flower;
                });
                return {...tree, flowerList: newFlowerList};
            }
            return tree;
        });
        onUpdate(nextTrees);
        onSelect(targetFlower);
    };

    const onClickTest = async () => {
        let startTime = new Date();

        console.log( "-------------------Test Start---------------------")
        // 나무 3개 심고 개화 총 10번 반복
        for (let i=0; i<10; i++) {
            await axios.get(`http://localhost:8080/march/tree/3`);
            await axios.get(`http://localhost:8080/march/flower/blossom`);
        }
        const firstEnd = new Date();
        console.log ("First process time: " + (firstEnd - startTime));

        // 다발 만들기 30번
        for (let i=0; i<30; i++) {
            await axios.post("http://localhost:8080/march/bundle/tempTitle", [{id: i, content: "test"}]);
        }
        const secondEnd = new Date();
        console.log("Second process time: " + (secondEnd - firstEnd));

        // 다발 호출 요청 후 제목 변경 30번
        let targetId = 0;
        await axios.get('http://localhost:8080/march/bundle').then(response => {
            targetId = response.data[0].id;
        })
        for (let i=0; i<30; i++) {
            await axios.put(`http://localhost:8080/march/bundle/${targetId}/${i}`);
        }
        const thirdEnd = new Date();
        console.log("Third process time: " + (thirdEnd - secondEnd));

        console.log("Total process time: " + (thirdEnd - startTime));
        console.log( "-------------------Test End---------------------")
    }

    const bundleState = trees.length === 0 ? <div></div> :
            (<div className="bundle-state">
                <div className="flower-count">선택된 꽃의 개수: {bundle.length}</div>
                <button className="make-button" onClick={onMake}>글귀 만들기</button>
            </div>);

    return (
        <div>
            <div className="title">Gift of March</div>
            <div className="menu">
                <input className="tree-input" type="text" value={num} placeholder="심을 나무의 개수를 입력" onChange={onNumChange} />
                <button className="tree-button" onClick={onClickPlant}>나무 심기</button>
                <button className="blossom-button" onClick={onClickBlossom}>개화</button>
                <Link className="bundle-link" to="/bundles">글귀 확인하기</Link>
                <button className="test-button" onClick={onClickTest}>성능 테스트</button>
            </div>
            <hr/>
            {bundleState}
            <TreesContainer trees={trees} onClickFlower={onClickFlower}/>
        </div>
    )
}

export default Home;