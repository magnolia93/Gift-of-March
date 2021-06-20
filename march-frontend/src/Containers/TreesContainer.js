import React from 'react';

import Tree from '../Components/Tree';

const TreesContainer = ({ trees, onClickFlower }) => {
    // trees를 Tree 리스트로 렌더링
    const treeList = trees.map(tree => <Tree key={tree.id} id={tree.id} flowerList={tree.flowerList} onClickFlower={onClickFlower}/>);
    return (
        <div className="TreesContainer">
            {treeList}
        </div>
    )
};

export default TreesContainer;