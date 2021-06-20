import { createAction, handleActions } from 'redux-actions';

const INIT = 'tree/INIT';
const UPDATE = 'tree/UPDATE';
const BLOSSOM = 'tree/BLOSSOM';
const SELECT = 'tree/SELECT';

export const init = createAction(INIT);
export const update = createAction(UPDATE);
export const blossom = createAction(BLOSSOM);
export const select = createAction(SELECT);

const initialState = {
    trees: [],
    bundle: [],
};

const treeStore = handleActions(
    {
        [INIT]: (state, {payload: initTrees}) => ({...state, bundle: [], trees: initTrees}),
        [UPDATE]: (state, {payload: newTrees}) => ({...state, trees: newTrees }),
        [BLOSSOM]: (state, {payload: blossomTree}) => ({...state, trees: blossomTree}),
        [SELECT]: (state, {payload: targetFlower}) => {
            
            // 체크된 상태에서 또 체크된 경우
            if (targetFlower.checked) {
                const afterRemove = state.bundle.filter(flower => flower.id !== targetFlower.id);
                return {...state, bundle: afterRemove};
            }

            // 이미 보내진 꽃잎인 경우
            if (targetFlower.sent) {
                return state;
            }

            if (state.bundle && state.bundle.length >= 3) {
                return state;
            }

            // 체크되지 않은 상태에서 체크되었고 해당 꽃이 이미 보내진 꽃이 아니라면 bundle에 추가.
            return {...state, bundle: [...state.bundle, targetFlower]};
        }
    },
    initialState,
);

export default treeStore;