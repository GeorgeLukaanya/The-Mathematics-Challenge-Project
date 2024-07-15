<?php

// app/Http/Controllers/AnswerController.php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Answer;
use Maatwebsite\Excel\Facades\Excel;
use App\Imports\AnswersImport;

class AnswerController extends Controller
{
    public function create()
    {
        return view('upload');
    }

    public function store(Request $request)
    {
        $request->validate([
            'file' => 'required|mimes:xlsx,xls'
        ]);

        Excel::import(new AnswersImport, $request->file('file'));

        return back()->with('success', 'Answers imported successfully.');
    }
}
