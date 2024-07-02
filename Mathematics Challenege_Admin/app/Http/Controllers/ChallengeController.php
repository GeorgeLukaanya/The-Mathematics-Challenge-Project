<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Challenge;

class ChallengeController extends Controller
{
    public function create()
    {
        return view('pages.user-profile');
    }

    public function store(Request $request)
    {
        $request->validate([
            'name' => 'required|string|max:255',
            'open_date' => 'required|date',
            'close_date' => 'required|date',
            'duration' => 'required|integer',
            'question_count' => 'required|integer',
        ]);

        $challenge = new Challenge();
        $challenge->name = $request->name;
        $challenge->open_date = $request->open_date;
        $challenge->close_date = $request->close_date;
        $challenge->duration = $request->duration;
        $challenge->question_count = $request->question_count;
        $challenge->save();

        return redirect()->route('challenges.create')->with('success', 'Challenge created successfully!');
    }
}
