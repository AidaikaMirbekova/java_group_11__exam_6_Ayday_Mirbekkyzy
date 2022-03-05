<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>books</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;600;700;800&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <title>Календарь</title>
    <link rel="stylesheet" href="/css/form.css">
</head>
<body>
<div class="main-content">
    <div class="month month--feb" style="--color: #d5c6c6">March</div>
    <#list dayList as day>
    <div class="date borders" style="--color:#f">
        <a href="/page?${day.days}" id="${day.days}">${day.days}
            <p>
                <#list day.notes as note>
                <img class="text-center" src="${note.photo}" width="30" height="30">
            </#list>
            </p>
        </a>
    </div>
</#list>
</div>
</body>
</html>