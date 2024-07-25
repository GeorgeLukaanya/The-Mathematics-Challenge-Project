<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use DB;

class AnalyticsController extends Controller
{
    public function mostCorrectlyAnswered()
    {
        // Logic to get most correctly answered questions
        $results = DB::table('results')
                    ->select('question', DB::raw('COUNT(correct) as correct_count'))
                    ->where('correct', 1)
                    ->groupBy('question')
                    ->orderBy('correct_count', 'desc')
                    ->get();

        return view('analytics.mostCorrectlyAnswered', ['results' => $results]);
    }

    public function schoolRankings()
    {
        // Logic to get school rankings
        $results = DB::table('results')
                    ->select('school', DB::raw('SUM(Total_score) as total_marks'))
                    ->groupBy('school')
                    ->orderBy('total_marks', 'desc')
                    ->get();

        return view('analytics.schoolRankings', ['results' => $results]);
    }

    public function performanceOverYears()
    {
        // Logic to get performance of schools and participants over the years
        $results = DB::table('results')
                    ->select('year', 'school', DB::raw('SUM(Total_score) as total_marks'))
                    ->groupBy('year', 'school')
                    ->orderBy('year', 'asc')
                    ->get();

        return view('analytics.performanceOverYears', ['results' => $results]);
    }

    public function questionRepetition()
    {
        // Logic to get percentage repetition of questions for a given participant across attempts
        // Define your own formula
        $results = DB::table('results')
                    ->select('participant', 'questions', DB::raw('COUNT(*) as attempts'), DB::raw('COUNT(DISTINCT questions) as unique_questions'))
                    ->groupBy('participant', 'questions')
                    ->get();

        return view('analytics.questionRepetition', ['results' => $results]);
    }

    public function worstPerformingSchools()
    {
        // Logic to get list of worst performing schools for a given challenge
        $results = DB::table('results')
                    ->select('school', DB::raw('SUM(Total_score) as total_marks'))
                    ->groupBy('school')
                    ->orderBy('total_marks', 'asc')
                    ->get();

        return view('analytics.worstPerformingSchools', ['results' => $results]);
    }

    public function bestPerformingSchools()
    {
        // Logic to get list of best performing schools for all challenges
        $results = DB::table('results')
                    ->select('school', DB::raw('SUM(Total_score) as total_marks'))
                    ->groupBy('school')
                    ->orderBy('total_marks', 'desc')
                    ->get();

        return view('analytics.bestPerformingSchools', ['results' => $results]);
    }

    public function incompleteChallenges()
    {
        // Logic to get list of participants with incomplete challenges
        $results = DB::table('results')
                    ->select('participant', DB::raw('COUNT(*) as total_attempts'), DB::raw('SUM(completed) as completed_attempts'))
                    ->havingRaw('total_attempts > completed_attempts')
                    ->groupBy('participant')
                    ->get();

        return view('analytics.incompleteChallenges', ['results' => $results]);
    }
}

