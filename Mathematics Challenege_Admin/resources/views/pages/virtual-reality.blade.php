@extends('layouts.app', ['class' => 'g-sidenav-show bg-gray-100'])

@section('content')
    @include('layouts.navbars.auth.topnav', ['title' => 'Generate Analytics'])
    <div class="container-fluid py-4">




    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generate Report</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center mt-5">
                <h1>Generate Analytics for the Mathematics Challenge</h1>
                <!-- <button id="generateReportBtn" class="btn btn-primary mt-3">Generate Report</button> -->


                <a href="{{ route('report.correct-answers') }}" class="btn btn-primary">Most Correctly Answered Questions</a>
                <a href="{{ route('report.school-rankings') }}" class="btn btn-primary">School Rankings</a>
                <a href="{{ route('report.performance-over-years') }}" class="btn btn-primary">Performance Over Years</a>
                <a href="{{ route('report.repeated-questions') }}" class="btn btn-primary">Repeated Questions</a>
                <a href="{{ route('report.worst-performing-schools') }}" class="btn btn-primary">Worst Performing Schools</a>
                <a href="{{ route('report.best-performing-schools') }}" class="btn btn-primary">Best Performing Schools</a>
                <a href="{{ route('report.incomplete-challenges') }}" class="btn btn-primary">Incomplete Challenges</a>

            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


       
        <!-- @include('layouts.footers.auth.footer') -->
    </div>
@endsection