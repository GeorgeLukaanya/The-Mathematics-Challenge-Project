<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Question Repetition</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Question Repetition</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Participant</th>
                    <th>Question</th>
                    <th>Attempts</th>
                    <th>Unique Questions</th>
                </tr>
            </thead>
            <tbody>
                @foreach ($results as $result)
                    <tr>
                        <td>{{ $result->participant }}</td>
                        <td>{{ $result->questions }}</td>
                        <td>{{ $result->attempts }}</td>
                        <td>{{ $result->unique_questions }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</body>
</html>

