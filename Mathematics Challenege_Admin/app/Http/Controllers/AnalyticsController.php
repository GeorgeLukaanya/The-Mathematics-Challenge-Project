<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Question; // Import the Question model
use App\Models\School;   // Import the School model
use Illuminate\Support\Facades\DB;

class AnalyticsController extends Controller
{
    //Correct Answers
    public function correctAnswersReport()
{
    $questions = Question::withCount('correctAnswers')->orderBy('correct_answers_count', 'desc')->get();
    return view('report.correct-answers', compact('questions'));
}

//Schools Ranking
public function schoolRankingsReport()
{
    $schools = School::with('students')->get()->sortByDesc(function($school) {
        return $school->students->avg('marks');
    });
    return view('report.school-rankings', compact('schools'));
}


//Performance over years
public function performanceOverYearsReport()
{
    $performances = DB::table('students')
        ->select(DB::raw('YEAR(created_at) as year'), DB::raw('AVG(marks) as average_marks'))
        ->groupBy('year')
        ->orderBy('year')
        ->get();
    return view('report.performance-over-years', compact('performances'));
}

//Repeated Questions
public function repeatedQuestionsReport(Request $request)
{
    $participantId = $request->input('participant_id');
    $repeatedQuestions = DB::table('answers')
        ->select('question_id', DB::raw('COUNT(*) as count'))
        ->where('participant_id', $participantId)
        ->groupBy('question_id')
        ->having('count', '>', 1)
        ->get();
    return view('report.repeated-questions', compact('repeatedQuestions'));
}

//Worst performing Schools
public function worstPerformingSchoolsReport(Request $request)
{
    $challengeId = $request->input('challenge_id');
    $schools = School::with(['students' => function($query) use ($challengeId) {
        $query->where('challenge_id', $challengeId);
    }])->get()->sortBy('students.avg(marks)');
    return view('report.worst-performing-schools', compact('schools'));
}

//Best performing Schools
public function bestPerformingSchoolsReport()
{
    $schools = School::with('students')->get()->sortByDesc(function($school) {
        return $school->students->avg('marks');
    });
    return view('report.best-performing-schools', compact('schools'));
}


//Incomplete Challenge
public function incompleteChallengesReport()
{
    $participants = Participant::whereHas('challenges', function($query) {
        $query->where('status', 'incomplete');
    })->get();
    return view('report.incomplete-challenges', compact('participants'));
}

}
