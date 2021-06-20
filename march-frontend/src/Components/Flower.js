import React from 'react';
import flowerImage from '../flowers.png';
import whiteSpace from '../whiteSpace.png';

// flower image 저작권: <div>Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
// 이후 밑에 삽입하기
const Flower = ({ id, treeId, onClickFlower, sent, checked }) => {
    return (
        <div className="Flower" onClick={() => onClickFlower(treeId, id)}>
            {sent || checked ? <img src={whiteSpace} width="50" height="50" alt=""/> : <img src={flowerImage} width="50" height="50" alt="" draggable />}
        </div>
    );
}

export default Flower;