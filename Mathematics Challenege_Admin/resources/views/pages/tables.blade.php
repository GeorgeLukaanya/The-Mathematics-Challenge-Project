@extends('layouts.app', ['class' => 'g-sidenav-show bg-gray-100'])

@section('content')
    @include('layouts.navbars.auth.topnav', ['title' => 'Upload Representatives'])
    <div class="container-fluid py-4">



    <!-- resources/views/fileupload/create.blade.php -->
<!DOCTYPE html>
<html>
<head>
    <title>Upload File</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header text-center bg-primary text-white">
                <h2 class="text-center bg-primary text-white">Upload Question</h2>
            </div>
            <div class="card-body">
                @if (session('success'))
                    <div class="alert alert-success">
                        {{ session('success') }}
                    </div>
                @endif

                <form action="{{ route('fileupload.store') }}" method="POST" enctype="multipart/form-data">
                    @csrf
                    <div class="mb-3">
                        <label for="file" class="form-label">Choose File</label>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input form-control" id="file" name="file" required>
                            <label class="custom-file-label" for="file">Choose file...</label>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Upload</button>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.querySelector('.custom-file-input').addEventListener('change', function (e) {
            var fileName = document.getElementById("file").files[0].name;
            var nextSibling = e.target.nextElementSibling
            nextSibling.innerText = fileName
        })
    </script>
</body>
</html>

       
        <!-- @include('layouts.footers.auth.footer') -->
    </div>
@endsection
