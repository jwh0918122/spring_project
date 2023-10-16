async function removeFile(uuid) {
    try {
        const url = "/board/file/" + uuid;
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

document.addEventListener('click', (e) => {
    console.log(e.target);
    let button = e.target.closest('button');

    if (e.target.classList.contains('file-x')) {
        let uuidVal = button.dataset.uuid;
        console.log('uuidVal>>> ' + uuidVal);

        if (writer == sesId) {
            removeFile(uuidVal).then(result => {
                if (result = 1) {
                    alert('파일 삭제 성공');
                    e.target.closest('li').remove(); //li 삭제
                    location.reload(); //새로고침
                } else {
                    alert('파일 삭제 실패');
                }
            })

        } else {
            alert('작성자가 아닙니다.');
        }

    }
})