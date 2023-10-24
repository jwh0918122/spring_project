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
async function spreadCommentListFromServer(bno, page) {
    try {
        const url = "/comment/list/" + bno + '/' + page;
        const resp = await fetch(url);
        const result = await resp.json(); //리스트 받음
        return result;

    } catch (error) {
        console.log(error)
    }
}


//댓글 리스트 출력 함수
function printCommentList(bno, page = 1) { //page=1인거는 처음 뿌릴 때는 무조검 첫페이지 뿌리라고 한거(옵셔널 값은 가장 마지막에 입력)
    spreadCommentListFromServer(bno, page).then(result => {
        console.log(result); //result 는 PagingHandler ph(pgvo, totalCount, cmtList)

        const ul = document.getElementById("cmtListArea");
        if (result.cmtList.length > 0) {

            //다시 댓글을 뿌릴 때 기존 값 삭제 1page 경우
            if (page == 1) {
                ul.innerText = "";
            }

            let str = '';
            for (let cvo of result.cmtList) {
                str += ` <li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                str += `<div>`;
                str += `<div class="fw-bold">${cvo.writer}</div>${cvo.content}`;
                // str += `<input type="text" id="cmtTextMod" value="${cvo.content}" class="form-control">`;
                str += `</div>`;
                str += `<span class="badge rounded-pill text-bg-secondary">${cvo.modAt}</span>`;
                str += `<div><button type="button" class="modBtn btn btn-warning" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                str += `<button type="button" class="delBtn btn btn-warning"">삭제</button></div></li>`;
            }
            ul.innerHTML += str;
            //str += `</ul>`;

            //댓글 페이징 코드
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn, result.pgvo.pageNo, result.endPage);
            //db에서 pgvo + list 같이 가져와야 값을 줄 수 있음.
            if (result.pgvo.pageNo < result.endPage) {
                moreBtn.style.visibility = 'visible'; //버튼 표시
                moreBtn.dataset.page = page + 1;
            } else {
                moreBtn.style.visibility = 'hidden'; //버튼 숨김
            }

        } else {
            ul.innerText = "댓글이 없습니다.";
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

//수정 함수
async function editCommentToServer(cmtDataMod) {
    try {
        const url = "/comment/modify";
        const config = {
            method: 'put',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//삭제,수정 함수 호출하여 댓글 삭제,수정
document.addEventListener('click', (e) => {
    // let writer = li.dataset.writer;
    // let content = li.querySelecter('#cmtTextMod');

    //삭제
    if (e.target.classList.contains('delBtn')) {
        let li = e.target.closest('li');
        let cno = li.dataset.cno;
        remove(cno).then(result => {
            if (result > 0) {
                alert('댓글 삭제 성공');

            } else {
                alert('댓글 삭제 실패');
            }
            printCommentList(bnoVal);
            document.getElementById('cmtText').focus();
        })
        //수정
    } else if (e.target.classList.contains('modBtn')) {
        let li = e.target.closest('li');
        //nextSibling() : 같은 부모의 다음 형제 객체를 반환 => ${cvo.content}
        let cmtText = li.querySelector('.fw-bold').nextSibling;

        //기존 내용 모달창에 반영(수정하기 편하게..)
        document.getElementById('cmtTextModal').value = cmtText.nodeValue;
        //cmtModBtn에 data-cno 달기
        document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);
    } else if (e.target.id == 'cmtModBtn') {
        let cmtDataMod = {
            cno: e.target.dataset.cno,
            content: document.getElementById('cmtTextModal').value
        };
        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result => {
            if (result > 0) {
                alert('댓글 수정 성공');
            } else {
                alert('댓글 수정 실패');
            }
            // 모달창 닫기
            document.querySelector('.btn-close').click();
            printCommentList(bnoVal);
        })
    } else if (e.target.id == 'moreBtn') {
        printCommentList(bnoVal, parseInt(e.target.dataset.page));
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