<!DOCTYPE html>
<head>
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
    <link rel="stylesheet" href="/css/form.css">
</head>
<body>
<a href="/" class="link">Back to Calendar</a>

<div class="table-wrapper">
    <table class="table table-hover">
       <#list notes as note>
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Category</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
                <th>${note?counter}</th>
                <td><img src="${note.photo}" width="50" height="50"></td>
                <td>${note.name}</td>
                <td>${note.description}</td>
        </tr>
        </tbody>
       </#list>
    </table>
</div>

</body>
</html>