console.log("bnoVal2>>> ", bnoVal);

//댓글 등록 함수 보내는 함수
async function postComment(cmtData) {
    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text(); //isOk
        return result;

    } catch (error) {
        console.log(error)
    }

}

//댓글 등록 함수 호출해서 등록
document.getElementById("cmtPostBtn").addEventListener('click', () => {
    const cmtText = document.getElementById("cmtText").value;
    const cmtWriter = document.getElementById("cmtWriter").innerText;

    let cmtData = {
        bno: bnoVal,
        writer: cmtWriter,
        content: cmtText
    }

    postComment(cmtData).then(result => {
        if (result > 0) {
            alert('댓글 등록 완료');


        } else {
            alert('댓글 등록 실패');
        }
        //전체 댓글 출력
        printCommentList(bnoVal);
        document.getElementById("cmtText").value = '';
        document.getElementById("cmtText").focus();
    })

})

//댓글 요청 함수
async function spreadCommentListFromServer(bno) {
    try {
        const url = "/comment/list/" + bno;
        const resp = await fetch(url);
        const result = await resp.json(); //리스트 받음
        return result;

    } catch (error) {
        console.log(error)
    }
}


//댓글 리스트 출력 함수
function printCommentList(bno) {
    spreadCommentListFromServer(bno).then(result => {
        const ul = document.getElementById("cmtListArea");
        if (result.length > 0) {
            let str = '';
            for (let cvo of result) {
                str += ` <li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                str += `<div>`;
                str += `<div>${cvo.writer}</div>Content`;
                str += `<input type="text" id="cmtTextMod" value="${cvo.content}" class="form-control">`;
                str += `</div>`;
                str += `<span class="badge rounded-pill text-bg-secondary">${cvo.modAt}</span>`;
                str += `<div><button type="button" class="modBtn" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                str += `<button type="button" class="delBtn">삭제</button></div></li>`;
            }
            str += `</ul>`;
            ul.innerHTML = str;
        } else {
            ul.innerText - "댓글이 없습니다.";
        }
    })
}

//삭제 함수
async function remove(cno) {
    try {
        const url = "/comment/remove/" + cno;
        const config = {
            method: "delete"
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}

//삭제,수정 함수 호출하여 댓글 삭제,수정
document.addEventListener('click', (e) => {
    let li = e.target.closest('li');
    let cno = li.dataset.cno;
    // let writer = li.dataset.writer;
    // let content = li.querySelecter('#cmtTextMod');
    if (e.target.classList.contains('delBtn')) {
        remove(cno).then(result => {
            if (result > 0) {
                alert('댓글 삭제 성공');

            } else {
                alert('댓글 삭제 실패');
            }
            printCommentList(bnoVal);
            document.getElementById('cmtText').focus();
        })
    }

})

// str += ` <li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
// str += `<div>`;
// str += `<div>${cvo.writer}</div>Content`;
// str += `<input type="text" id="cmtTextMod" value="${cvo.content}" class="form-control">`;
// str += `</div>`;
// str += `<span class="badge rounded-pill text-bg-secondary">${cvo.modAt}</span>`;
// str += `<div><button type="button" class="modBtn">수정</button>`;
// str += `<button type="button" class="delBtn">삭제</button></div></li>`;