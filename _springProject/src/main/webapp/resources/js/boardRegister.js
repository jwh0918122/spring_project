//트리거 버튼 처리
document.getElementById('trigger').addEventListener('click', () => {
    document.getElementById('files').click(); //files 아이디를 가지고 있는 것을 클릭



});
//실행파일, 이미지 파일에 대한 정규표현식 작성(시작은 \ 끝은 $표시)
const regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$"); //실행파일 막기(.은 확장자 구분하는것이기 때문에)
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$"); //이미지 파일만
const maxSize = 1024 * 1024 * 20; //파일 최대 사이즈(20MB)

function fileValidation(fileName, fileSize) {
    if (!regExpImg.test(fileName)) {
        return 0;
    } else if (regExp.test(fileName)) {
        return 0;
    } else if (fileSize > maxSize) {
        return 0;
    } else {
        return 1;
    }
}

document.addEventListener('change', (e) => { //'change' => 뭔가 변화가 생겼다면 그 객체에 이벤트 추가
    if (e.target.id == 'files') {

        //"files"란 id를 가진 input file element에 저장된 file의 정보를 가져오는 property
        const fileObj = document.getElementById('files').files;
        console.log(fileObj);

        //파일을 다시 추가할 때는 버튼 상태를 원래대로(활성화상태) 변경
        document.getElementById('regBtn').disabled = false; //regBtn 활성화

        /*첨부파일에 대한 정보를 fileZone에 뜨게 만들기*/
        let div = document.getElementById('fileZone');
        div.innerHTML = ''; // 'fileZone' 에 기존 값이 있으면 삭제

        //ul => li로 첨부파일 추가
        let isOk = 1; //여러 파일이 모두 검증에 통과해야 하기 때문에 *로 각 파일마다 통과여부 확인
        let ul = `<ul class="list-group list-group-flush">`;
        for (let file of fileObj) {
            let vaildResult = fileValidation(file.name, file.size); //0 또는 1로 리턴
            isOk *= vaildResult; //vaildResult => 현재 파일 통과 여부
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${vaildResult ? '<div class="mb-3">업로드 가능</div>' : '<div class="mb-3 text-danger">업로드 불가능</div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge text-bg-${vaildResult ? 'success' : 'danger'}">${file.size}Byte</span></li>`;

        }
        ul += `</ul>`;
        div.innerHTML = ul;
        if (isOk == 0) { //isOk => 전체 파일 통과 여부
            document.getElementById('regBtn').disabled = true; //regBtn 비활성화
        }
    }
})

// < ul class="list-group list-group-flush" >
// <li class="list-group-item">An item</li>
// </ul>