<?php

// app/Http/Controllers/QuestionController.php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Question;
use Maatwebsite\Excel\Facades\Excel;
use App\Imports\QuestionsImport;

class QuestionController extends Controller
{
    public function create()
    {
        return view('qn-upload');
    }

    public function store(Request $request)
    {
        $request->validate([
            'file' => 'required|mimes:xlsx,xls'
        ]);

        Excel::import(new QuestionsImport, $request->file('file'));

        return back()->with('success', 'Questions imported successfully.');
    }
}
