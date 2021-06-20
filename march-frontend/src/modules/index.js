import { combineReducers } from 'redux';
import treeStore from './treeStore';

const rootReducer = combineReducers({
    treeStore,
});

export default rootReducer;