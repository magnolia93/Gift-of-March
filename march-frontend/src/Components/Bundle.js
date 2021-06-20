import axios from 'axios';
import React, { useState } from 'react';

const Bundle = ({originTitle, contents, id, onDelete}) => {
    const [title, setTitle] = useState(originTitle);

    const onChangeTitle = (e) => {
        setTitle(e.target.value);
    }

    const onClickChange = () => {
        axios.put(`http://localhost:8080/march/bundle/${id}/${title}`).then(response => {
            setTitle(response.data.title);
        }).catch(e => console.log(e));
    }

    return (
        <div className="Bundle">
            <input className="bundle-title" type="text" value={title} id={id} onChange={onChangeTitle} />
            {contents}
            <button className="title-change-button" onClick={() => onClickChange(id, title)}>제목 변경</button>
            <button className="delete-button" onClick={() => onDelete(id)}>삭제 하기</button>
        </div>
    )
}


export default Bundle;