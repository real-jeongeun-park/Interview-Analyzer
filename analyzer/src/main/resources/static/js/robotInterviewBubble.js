// Description 부분
const questions = [
  "1분 자기소개를 해보세요.",
  "자신의 장점은 무엇이라 생각합니까?",
  "자신의 단점은 무엇이라 생각합니까?",
  "최근 읽은 책이나 인상 깊었던 경험은 무엇인가요?",
  "협업할 때 중요하게 생각하는 점은 무엇인가요?",
  "스트레스를 어떻게 해소하나요?",
  "실패했던 경험과 그때의 배운 점은 무엇인가요?",
  "본인의 성격을 세 단어로 표현한다면?",
  "지원한 분야에 관심을 갖게 된 계기는?",
  "앞으로의 목표는 무엇인가요?",
  "갈등 상황이 생겼을 때 어떻게 해결하나요?"
];

const guideStrings = [
  "룰은 간단합니다. 랜덤한 질문이 주어지고,\n그에 맞춰 1분 동안 답변을 해 주시면 돼요!",
  "면접을 끝내고 싶으면,\n밑에 있는 빨간색 버튼을 눌러주세요.",
  "면접이 끝나면 녹음된 음성과\n찍은 동영상을 받아볼 수 있어요.",
  "그럼 지금부터 10초 후에\n모의 면접을 시작할게요!"
]

const element = document.getElementById('guide');

let i = 0, l = 11, p = 0;
let isAlreadyUsed = Array(questions.length).fill(false); // 기본 false 가짐
let finishRecording = false;

// 규칙 설명
const intervalId = setInterval(() => {
  if (i >= guideStrings.length){
    clearInterval(intervalId);
    // timer 시작
    timeInterval();
  }

  else if(finishRecording){
    // 끝났으면
    return;
  }

  else{
    guide(i++);
  }
 }, 4000);

function guide(j){
  element.innerHTML = "<div class='speech-bubble fade-in text-center whitespace-pre'>" + guideStrings[j] + "</div>";
}

// 10초 타이머
function timeInterval(){
  const intervalId2 = setInterval(() => {
    if(l <= 0){
      clearInterval(intervalId2);
      // random question 시작
      questionInterval();
    }

    else if(finishRecording){
        // 끝났으면
        return;
    }

    else{
      timer(--l);
    }
  }, 1000);
}

function timer(m){
  element.innerHTML = "<div class='speech-bubble fade-in text-center w-[100px]'>" + l + "</div>";
}

// 질문 시작
function questionInterval(){
  // 레코딩도 시작
  // 일단 주석 처리
  var errorCatch = startRecording();
  if(errorCatch) return; // 오류 떴을 경우(카메라 허용하지 않음) true 반환됨 -> 종료

  // 첫 질문 defualt로 띄우기
  const firstQuestion = 0;
  isAlreadyUsed[firstQuestion] = true;
  element.innerHTML = "<div class='speech-bubble fade-in text-center'>" + questions[firstQuestion] + "<span id='timeChecker'> (60초 남음)</span></div>";
  questionTimer();

  const intervalId3 = setInterval(() => {
    if(p >= questions.length){
      // 더이상 질문 없음
      clearInterval(intervalId3);
    }

    else if(finishRecording){
        // 끝났으면
        return;
    }

    else{
      question(p++);
      questionTimer();
    }
  }, 60000);// 60초. 1분 진행. 테스트는 10초 진행.
}

function startRecording(){
  if(!isRecording){
    // false라면 바로 실행 시키기. true이면 그대로 둠.
    try{
       mediaRecorder.start();
    } catch(e){
        element.innerHTML = "<div class='speech-bubble fade-in text-center text-red-500 whitespace-pre'>⚠️ 카메라 사용 권한이 없어요!\n허용을 선택한 후, 새로고침 하세요.</div>";
        return true;
    }

    isRecording = true;
    startBtn.classList.add('ring-4', 'ring-red-400', 'animate-pulse');
    return false; // errorCatch는 false가 됨
  }
 }

function question(p){
// p는 단순히 횟수 셈
   while(true){
      var randomInt = randomIntGenerator(questions.length);
      if(!isAlreadyUsed[randomInt]){
        element.innerHTML = "<div class='speech-bubble fade-in text-center'>" + questions[randomInt] + "<span id='timeChecker'> (60초 남음)</span></div>";
        isAlreadyUsed[randomInt] = true;
        break;
      }
   }
}

function randomIntGenerator(size){
    return Math.floor(Math.random() * 10) % size;
}

function questionTimer(){
  let time = 60;
  let timeChecker = document.getElementById('timeChecker');

  const intervalId4 = setInterval(() => {
    if(time <= 0){
      // 0초가 되면
    clearInterval(intervalId4)
    }

    else if(finishRecording){
        // 끝났으면
        return;
    }

    else{
      timeChecker.textContent = ' (' + --time + '초 남음)';
    }
  }, 1000);
}