const preview = document.getElementById('preview');
const recorded = document.getElementById('recorded');
const recordedAudio = document.getElementById('recordedAudio');
const recordedContainer = document.getElementById('recordedContainer');
const resultContainer = document.getElementById('resultContainer');
const startBtn = document.getElementById('startBtn');
const downloadBtn = document.getElementById('downloadBtn');

let jsonData;


let mediaRecorderVideo;
let mediaRecorderAudio;
let recordedVideoChunks = [];
let recordedAudioChunks = [];
let isRecording = false;

async function initCamera() {
  const stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
  preview.srcObject = stream;

  // 오디오 있는 비디오 받기
  mediaRecorderVideo = new MediaRecorder(stream, { mimeType: 'video/webm' });

  mediaRecorderVideo.ondataavailable = (e) => {
    if (e.data.size > 0) recordedVideoChunks.push(e.data);
  };

  mediaRecorderVideo.onstop = () => {
    const videoBlob = new Blob(recordedVideoChunks, { type: 'video/webm' });
    const videoURL = URL.createObjectURL(videoBlob);
    recorded.src = videoURL;
    recordedContainer.classList.remove("hidden");

    downloadBtn.href = videoURL;
    downloadBtn.classList.remove("hidden");

    recordedVideoChunks = [];
  };

  // 오디오만 받기
  const audioTracks = stream.getAudioTracks();
  const audioStream = new MediaStream(audioTracks);
  mediaRecorderAudio = new MediaRecorder(audioStream, { mimeType: 'audio/webm' });

  mediaRecorderAudio.ondataavailable = (e) => {
    if (e.data.size > 0) recordedAudioChunks.push(e.data);
  };

  mediaRecorderAudio.onstop = async () => {
    const audioBlob = new Blob(recordedAudioChunks, { type: 'audio/webm' });
    const formData = new FormData();
    formData.append('file', audioBlob, 'audio.webm');

    try{
        const response = await fetch('/home/interview/transcribe', {
            method: 'POST',
            body: formData
        });

        const data = await response.json();
        jsonData = JSON.stringify(data);

        resultContainer.classList.remove("hidden");
    } catch(e){
        console.error(e);
    }
    recordedAudioChunks = [];
  };
}


startBtn.addEventListener('click', () => {
  if (!isRecording) {
    mediaRecorderVideo.start();
    mediaRecorderAudio.start();

    isRecording = true;
    startBtn.classList.add('ring-4', 'ring-red-400', 'animate-pulse');
  }

  else {
    mediaRecorderVideo.stop();
    mediaRecorderAudio.stop();

    element.innerHTML = "<div class='speech-bubble right-bubble fade-in text-center whitespace-pre'>모의 면접이 종료됐어요.\n아래의 동영상과 모의 면접 결과를 확인하세요.</div>";
    isRecording = false;
    finishRecording = true; // 더이상 질문 올라오지 x
    startBtn.classList.remove('ring-4', 'ring-red-400', 'animate-pulse');
    startBtn.classList.add('cursor-not-allowed');
  }
});

initCamera();

async function resultCheck(){
    console.log(jsonData);
    try{
    const result = await fetch('/home/interview/result', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonData,
    });
    } catch(e){
        console.error(e);
    }
}