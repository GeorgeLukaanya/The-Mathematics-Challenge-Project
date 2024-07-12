<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\School;

class SchoolController extends Controller
{
    public function store(Request $request)
    {
        $validatedData = $request->validate([
            'name' => 'required|string|max:255',
            'district' => 'required|string|max:255',
            'reg_no' => 'required|string|max:255|unique:schools',
            'email' => 'required|string|email|max:255|unique:schools',
            'rep_name' => 'required|string|max:255',
        ]);

        $school = new School();
        $school->name = $validatedData['name'];
        $school->district = $validatedData['district'];
        $school->reg_no = $validatedData['reg_no'];
        $school->email = $validatedData['email'];
        $school->rep_name = $validatedData['rep_name'];
        $school->save();

        return redirect()->back()->with('success', 'School information saved successfully!');
    }
}
