<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>School Rankings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>School Rankings</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>School</th>
                    <th>Average Marks</th>
                </tr>
            </thead>
            <tbody>
                @foreach($schools as $school)
                <tr>
                    <td>{{ $school->name }}</td>
                    <td>{{ $school->students->avg('marks') }}</td>
                </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</body>
</html>
