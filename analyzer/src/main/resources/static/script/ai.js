    $('#chatForm').on('submit', function(e){
      e.preventDefault();

      const input = $('#userInput');

      const promptRequest = input.val();
      addMessage('user', promptRequest);
      input.val('');
<!--      입력창 초기화  -->

      $.ajax({
        type: 'POST',
        url: '/home/ai/asked',
        contentType: 'application/json',
        data: JSON.stringify({prompt: promptRequest}),
        success: function(data){
            const aiResponse = data.answer;
            addMessage('bot', aiResponse);
        }
      });
    });

    function addMessage(role, text){
      const chatContainer = $('#chatContainer')[0];
      const div = document.createElement('div');
      div.className = `fade-in p-4 rounded-lg shadow text-sm ${role === 'user' ? 'bg-blue-100 text-right' : 'bg-white text-left'}`;
      div.innerHTML = `<strong>${role === 'user' ? '🙋‍♀️ 나' : '🤖 챗봇'}:</strong> ${text}`;
      chatContainer.appendChild(div);
      chatContainer.scrollTop = chatContainer.scrollHeight;
    }
