console.log(bnoVal);
async function postCommentToServer(cmtData) {
    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            Headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config)
        const result = await resp.text(); //isOk
        return result;
    } catch (error) {
        console.log(error)
    }



    document.getElementById("cmtPostBtn").addEventListener('click', () => {

        const cmtText = document.getElementById("cmtText").value;
        const cmtWriter = document.getElementById("cmtWriter").innerText;  //span태그 안에 있는 데이터는 innerText로 가져와야 함
        if (cmtText == "" || cmtText == null) {
            alert("댓글을 입력해주세요")
            document.getElementById("cmtText").focus();
            return false;
        } else {
            let cmtData = {
                bno: bnoVal,
                writer: cmtWriter,
                content: cmtText
            };
            console.log(cmtData);
            postCommentToServer(cmtData).then(result => {
                console.log(result);
                //isOk 확인
                if (result == 1) {
                    alert("댓글 등록 성공!~");
                    //화면에 뿌리기
                }

            })
        }


    })



}