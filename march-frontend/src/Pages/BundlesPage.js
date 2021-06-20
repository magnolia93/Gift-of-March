import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import BundleList from '../Components/BundleList';
import './BundlesPage.css';

const BundlesPage = () => {
    const [bundles, setBundles] = useState([]);
    const onClickBundles = () => {
        axios.get('http://localhost:8080/march/bundle').then(response => {
            console.log(response.data);
            setBundles(response.data);
        });
    };

    const onClickDelete = (id) => {
        axios.delete(`http://localhost:8080/march/bundle/${id}`).then(response => {
            const newBundles = bundles.filter(b => b.id !== id);
            setBundles(newBundles);
        });
    }

    return (
        <div className="BundlesPage">
            <div className="bundle-menu">
                <Link className="go-back" to="/">돌아가기</Link>
                <button className="load-bundles" onClick={onClickBundles}>글귀 불러오기</button>
            </div>
            <BundleList bundles={bundles} onDelete={onClickDelete}/>
        </div>
    )
}

export default BundlesPage;