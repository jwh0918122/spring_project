console.log(bnoVal);

//댓글 등록하는 함수
async function postCommentToServer(cmtData) {
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

//댓글 등록하는 함수 호출
document.getElementById('cmtPostBtn').addEventListener('click', () => {
    console.log("dfddfdfdfddd");
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
                getCommentList(bnoVal);
            }

        })
    }
    document.getElementById("cmtText").value = "";
})
//리스트 달라고 요청 함수
async function spreadCommentListFromServer(bno) {
    try {
        const resp = await fetch("/comment/" + bno); //method 안적으면 get방식
        const result = await resp.json(); //리스트니까 객체로 받아옴(json)
        return result
    } catch (error) {
        console.log(error)
    }
}
//리스트 요청 함수 호출
function getCommentList(bno) {
    spreadCommentListFromServer(bno).then(result => {
        console.log(result);
        //화면에 뿌리기
        if (result.length > 0) {
            //리스트 뿌리는 함수 호출
            list(result);
        } else {
            let cmtListArea = document.getElementById("cmtListArea");
            cmtListArea.innerText = "댓글이 없습니다.";
        }


    })
}

//리스트 뿌리는 함수
function list(result) {
    console.log(result);
    let cmtListArea = document.getElementById("cmtListArea");
    cmtListArea.innerHTML = '';
    // for (let i = 0; i < result.length; i++) {
    //     let str = `<li data-cno="${result[i].cno}"><div><div>${result[i].writer}</div>`;
    //     str += `<input type="text" id="cmtTextMod" value="${result[i].content}"></div>`;
    //     str += `<span>${result[i].reg_date}</span>`;
    //     str += `<div><button type="button" id="modBtn">수정</button>`;
    //     str += `<button type="button" id="delBtn">삭제</button></div></li>`;
    //     cmtListArea.innerHTML += str;
    // }
    for (let cvo of result) {
        let str = `<li data-cno="${cvo.cno}" data-writer="${cvo.writer}"><div><div>${cvo.writer}</div>`;
        str += `<input type="text" id="cmtTextMod" value="${cvo.content}"></div>`;
        str += `<span>${cvo.reg_date}</span>`;
        str += `<div><button type="button" class="modBtn">수정</button>`;
        str += `<button type="button" class="delBtn">삭제</button></div></li>`;
        cmtListArea.innerHTML += str;
    }
}

//수정 요청 함수 
async function editCommentToServer(cmtModData) {
    try {
        // const url = "/comment/edit"; 
        const url = "/comment/" + cmtModData.cno;
        const config = {
            method: 'put',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtModData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
//삭제 요청 함수
async function removeCommentToServer(cno, writer) {
    try {
        const url = "/comment/" + cno + "/" + writer;
        const config = {
            method: 'delete'
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}


// let str = `<li data-cno="${cvo.cno}"><div><div>${cvo.writer}</div>`;
// str += `<input type="text" id="cmtTextMod" value="${cvo.content}"></div>`;
// str += `<span>${cvo.reg_date}</span>`;
// str += `<div><button type="button" class="modBtn">수정</button>`;
// str += `<button type="button" class="delBtn">삭제</button></div></li>`;
// cmtListArea.innerHTML += str;

//수정,삭제 요청 함수 호출
document.addEventListener('click', (e) => {
    console.log(e.target);
    let li = e.target.closest('li');//내가 선택한 target과 가장 가까운 li 찾는거 
    let cnoVal = li.dataset.cno; //li에 달아논 cno 데이터 값을 cnoVal에 넣기        
    let textContent = li.querySelector('#cmtTextMod').value;
    let writerVal = li.dataset.writer; //작성한 id
    let cmtWriter = document.getElementById("cmtWriter").innerText; //현재 ses.id
    console.log('cno/content/writer=> ' + cnoVal + " " + textContent + " " + cmtWriter);

    if (e.target.classList.contains('modBtn')) {
        //수정작업
        console.log('수정버튼 클릭~!!');
        if (writerVal == cmtWriter) {//sesid와 writer가 같으면 
            let cmtModData = {
                cno: cnoVal,
                writer: writerVal,
                content: textContent
            };
            console.log(cmtModData);

            //서버 연결
            editCommentToServer(cmtModData).then(result => {
                if (result == 1) {
                    alert('댓글 수정 성공!');

                } else if (result == 2) {
                    alert('작성자가 일치하지 않습니다.')
                } else {
                    alert('댓글 수정 실패');
                }
                getCommentList(bnoVal);
            })
        } else {
            alert(`${cmtWriter}님이 등록한 게시물만 수정 가능합니다.`)
        }
    } else if (e.target.classList.contains('delBtn')) {
        //삭제작업
        console.log('삭제버튼 클릭~!!');

        if (writerVal == cmtWriter) {
            //서버 연결
            removeCommentToServer(cnoVal, writerVal).then(result => {
                if (result == 1) {
                    alert('댓글 삭제 성공');

                } else if (result == 2) {
                    alert('작성자가 일치하지 않습니다.')
                } else {
                    alert('댓글 삭제 실패');
                }
                getCommentList(bnoVal);
            })
        } else {
            alert(`${cmtWriter}님이 등록한 게시물만 삭제 가능합니다.`)
        }


    }
})



