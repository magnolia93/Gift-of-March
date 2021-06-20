import React from 'react';
import Bundle from './Bundle';

const BundleList = ({bundles, onDelete}) => {
    const res = bundles.map(bundle => 
        (<Bundle key={bundle.id} id={bundle.id} originTitle={bundle.title} contents={bundle.contents} 
                onDelete={onDelete}
        />)
        );
    return (
        <div className="BundleList">
            {res}
        </div>
    )
}

export default BundleList;