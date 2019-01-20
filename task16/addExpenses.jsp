<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>AddExpenses</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Add Expenses</h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <form method="post" action="AddExpenses" class="w3-selection w3-light-grey w3-padding">
            <label>Pay Date:
                <input type="date" name="paydate" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Receiver:
                <input type="number" name="receiver" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Value:
                <input type="number" name="value" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">ADD</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/a-1.0/Task15'">Back to main</button>
</div>
</body>
</html>