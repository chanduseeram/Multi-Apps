<%@ page import="com.example.utils.NumberToWord, com.example.utils.NumberToWordFirst" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Number to Word Converter</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
    }

    body {
      font-family: 'Segoe UI', sans-serif;
      background-color: #f2f4f8;
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 20px;
    }

    .converter-box {
      background: #fff;
      width: 100%;
      max-width: 500px;
      border-radius: 12px;
      padding: 24px;
      box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
      display: flex;
      flex-direction: column;
      gap: 16px;
    }

    h2 {
      font-size: 1.5rem;
      color: #333;
      text-align: center;
    }

    .input-wrapper {
      position: relative;
    }

    .input-wrapper input {
      width: 100%;
      padding: 12px 12px 12px 12px;
      font-size: 16px;
      border: 1px solid #ccc;
      border-radius: 8px;
      outline: none;
    }

    button {
      width: 100%;
      padding: 12px;
      font-size: 16px;
      border: none;
      border-radius: 8px;
      background-color: deepskyblue;
      color: white;
      cursor: pointer;
      transition: background-color 0.2s ease;
    }

    button:hover {
      background-color: #0056b3;
    }

    #result {
      font-size: 1rem;
      color: #333;
      padding: 12px;
      background: #f9f9f9;
      border-radius: 8px;
      word-break: break-word;
    }

    @media (max-width: 375px) {
      .converter-box { padding: 16px; max-width: 100%; }
      h2 { font-size: 1.2rem; }
      input, button, #result { font-size: 14px; }
    }

    @media (min-width: 376px) and (max-width: 425px) {
      .converter-box { padding: 18px; max-width: 100%; }
      h2 { font-size: 1.25rem; }
      input, button, #result { font-size: 15px; }
    }

    @media (min-width: 426px) and (max-width: 767px) {
      .converter-box { padding: 20px; }
      h2 { font-size: 1.35rem; }
      input, button, #result { font-size: 15.5px; }
    }

    @media (min-width: 768px) and (max-width: 1023px) {
      .converter-box { padding: 24px; max-width: 600px; }
      h2 { font-size: 1.5rem; }
      input, button, #result { font-size: 16px; }
    }

    @media (min-width: 1024px) {
      .converter-box { padding: 28px; max-width: 700px; }
      h2 { font-size: 1.75rem; }
      input, button, #result { font-size: 17px; }
    }
  </style>
</head>
<body>

<div class="converter-box">
  <h2>Number to Words</h2>
  <form id="convertForm" method="get">
    <div class="input-wrapper">
      <input type="tel" id="number" name="number" maxlength="12" placeholder="Enter a number" required>
    </div>
    <br>
    <button type="submit">Convert</button>
  </form>

<%
String input2 = request.getParameter("number");
if (input2 != null && !input2.trim().isEmpty()) {
    try {
        input2 = input2.trim();
        int cutLength = 9;
        int input = 0;
        String firstPart = "";
        String lastPart = "";
        String result9 = "";
        String result19 = "";

        if (input2.length() > cutLength) {
            firstPart = input2.substring(0, input2.length() - cutLength);
            lastPart = input2.substring(input2.length() - cutLength);
        } else {
            lastPart = input2;
        }

        int check = 0, check2 = 0;
        if (lastPart.length() >= 2) {
            check = Integer.parseInt(lastPart.substring(0, 1));
            check2 = Integer.parseInt(lastPart.substring(1, 2));
        }

        if (input2.length() <= cutLength) {
            int number = Integer.parseInt(input2);
            NumberToWord converter = new NumberToWord();
            String result = converter.test(number);
            result19 = input2 + " : " + result;
        } else {
            if (Integer.parseInt(firstPart) == 0) {
                input = Integer.parseInt(input2);
            }

            if (input == 0) {
                int numberFirst = Integer.parseInt(firstPart);
                NumberToWordFirst converter = new NumberToWordFirst();
                String result = converter.test(numberFirst);
                result19 = input2 + " : " + result;
            }

            int number = Integer.parseInt(lastPart);
            NumberToWord converter = new NumberToWord();
            String result = converter.test(number);
            if (check == 0 && check2 == 0) {
                result9 = " crore and " + result;
            } else {
                result9 = " and " + result;
            }
        }
%>
  <div id="result"><%= result19 + result9 %></div>
<%
    } catch (NumberFormatException e) {
%>
  <div id="result" style="color:red;">Please enter a number below 2,147,483,647</div>
<%
    }
}
%>

</div>

<script>
window.onload = function () {
  document.getElementById('convertForm').addEventListener('submit', function (e) {
    const input = document.getElementById('number').value.trim();
    const number = parseInt(input);
    const resultBox = document.getElementById('result');

    if (isNaN(number) || number < 0 || number > 100000000000000) {
      resultBox.textContent = "Please enter a valid number (0 - 100000000000000)";
      return;
    }

    fetch(`NumberToWord.jsp?number=${number}`)
      .then(response => response.text())
      .then(data => {
        resultBox.innerHTML = data.trim();
      })
      .catch(() => {
        resultBox.textContent = "Error occurred while converting.";
      });
  });
};
</script>

</body>
</html>
