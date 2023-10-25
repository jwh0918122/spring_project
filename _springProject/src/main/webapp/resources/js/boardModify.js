//파일 삭제 함수
async function deleteFile(uuid) {
    try {
        const url = "/board/fileDelete/" + uuid;
        const config = {
            method: 'delete'
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}

//파일 삭제 함수 호출
document.addEventListener('click', (e) => {
    let div = e.target.closest('div');
    let delBtn = div.querySelector('.fileDel');
    let uuid = delBtn.dataset.uuid;
    console.log("div :", div);
    console.log("delBtn :", delBtn);
    console.log("uuid :", uuid);

    deleteFile(uuid);
    location.reload();
})