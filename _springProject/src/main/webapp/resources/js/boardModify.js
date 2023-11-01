//파일 삭제 함수
async function deleteFile(uuid) {
    try {
        const url = "/board/fileDelete/" + uuid;
        const config = {
            method: 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}

//파일 삭제 함수 호출
// document.addEventListener('click', (e) => {
//     let div = e.target.closest('div');
//     let delBtn = div.querySelector('.fileDel');
//     let uuid = delBtn.dataset.uuid;
//     console.log("div :", div);
//     console.log("delBtn :", delBtn);
//     console.log("uuid :", uuid);

//     deleteFile(uuid);
//     location.reload();
// })


// - 강사님
//파일 삭제 함수 호출
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('fileDel')) {
        deleteFile(e.target.dataset.uuid).then(result => {
            console.log(result);
            alert('파일 삭제' + (parseInt(result) > 0) ? ' 완료' : ' 실패');
            if (parseInt(result)) { //result가 1이면 
                e.target.closest('div').remove();
                location.reload(); //새로고침
            }
        })
    }
})