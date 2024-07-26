<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Incomplete Challenges</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Participants with Incomplete Challenges</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Participant</th>
                    <th>Total Attempts</th>
                    <th>Completed Attempts</th>
                </tr>
            </thead>
            <tbody>
                @foreach ($results as $result)
                    <tr>
                        <td>{{ $result->participant }}</td>
                        <td>{{ $result->total_attempts }}</td>
                        <td>{{ $result->completed_attempts }}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</body>
</html>
