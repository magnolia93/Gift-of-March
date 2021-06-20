import React from 'react';

import Flower from './Flower';

const Tree = ({ id, flowerList, onClickFlower }) => {
    if (flowerList === null) return (<div className="Tree"></div>);

    const flowers = flowerList.map(flower => 
    <Flower key={flower.id} id={flower.id} 
        treeId={id} onClickFlower={onClickFlower} 
        phrase={flower.phrase} checked={flower.checked} sent={flower.sent} />);
    
    return (
        <div className="Tree">
            {flowers}
        </div>
    )
}

export default Tree;