document.getElementById('trigger').addEventListener('click', () => {
    //id가 trigger인 애를 누르면 file인 애를 누른것과 같은 효과
    document.getElementById('file').click();
})

//정규표현식을 사용하여 파일 형식제한 함수 만들기
//제한 : 실행파일 막기, 이미지 파일만 체크, 20M 사이즈보다 크면 막기
const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$"); //실행 파일 패턴(막아야 하는 거)
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif|bmt)$"); //이미지 파일 패턴(Ok해야 하는 거)
const maxSize = 1024 * 1024 * 20; //최대 사이즈 설정(20메가바이트)

//return값은 0 또는 1 (0은 안되는거,1은 가능한거)
function fileValidation(fileName, fileSize) {
    if (regExp.test(fileName)) { //실행파일이면
        return 0;
    } else if (fileSize > maxSize) { //파일 사이즈가 maxSize보다 크면
        return 0
    } else if (!regExpImg.test(fileName)) { //이미지 파일이 아니면
        return 0;
    } else {
        return 1;
    }
}

//첨부 파일에 따라 파일이 등록 가능한지 체크 함수
document.addEventListener('change', (e) => { //'change' : 어떠한 것이 들어오면...?
    console.log(e.target);
    if (e.target.id == 'file') {
        // document.getElementById('regBtn').disabled = false;
        const fileObject = document.getElementById('file').files; //여러개의 파일이 배열로 들어옴
        console.log(fileObject);
        let div = document.getElementById('fileZone');
        div.innerHTML = '';
        let ul = `<ul>`;
        let isOk = 1; //fileValidation 함수의 리턴여부를 *로 체크(하나라도 통과를 못하면 불가능으로 하기 위해서)
        for (let file of fileObject) {
            let validResult = fileValidation(file.name, file.size);
            isOk *= validResult; //모든 파일이 누적되어 *
            ul += `<li>`;
            //업로드 가능 여부 표시
            ul += `${validResult ? '<div>업로드 가능' : '<div>업로드 불가능'}</div>`; //valiResult가 0이면 false,1이면 true로 인식 해줌 
            ul += `${file.name}`;
            ul += `<span class="badge rounded-pill text-bg-${validResult ? 'success' : 'danger'}"> ${file.size} Byte</span></li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;
        if (isOk == 0) { //첨부 불가능한 파일이 있다는 것을 의미
            document.getElementById('regBtn').disabled = true;//버튼 비활성화
        }
    }
})

//ul => li 여러 개
//<li> <div>업로드 가능 or 불가능</div>
//파일이름 < span > 파일 사이즈</span ></li>