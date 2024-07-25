<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

use App\Http\Controllers\HomeController;
use App\Http\Controllers\PageController;
use App\Http\Controllers\RegisterController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\UserProfileController;
use App\Http\Controllers\ResetPassword;
use App\Http\Controllers\ChangePassword;  
use App\Http\Controllers\AdminController;   

use App\Http\Controllers\SchoolController;



use App\Http\Controllers\FileUploadController;
            

Route::get('/', function () {return redirect('/dashboard');})->middleware('auth');
	Route::get('/register', [RegisterController::class, 'create'])->middleware('guest')->name('register');
	Route::post('/register', [RegisterController::class, 'store'])->middleware('guest')->name('register.perform');
	Route::get('/login', [LoginController::class, 'show'])->middleware('guest')->name('login');
	Route::post('/login', [LoginController::class, 'login'])->middleware('guest')->name('login.perform');
	Route::get('/reset-password', [ResetPassword::class, 'show'])->middleware('guest')->name('reset-password');
	Route::post('/reset-password', [ResetPassword::class, 'send'])->middleware('guest')->name('reset.perform');
	Route::get('/change-password', [ChangePassword::class, 'show'])->middleware('guest')->name('change-password');
	Route::post('/change-password', [ChangePassword::class, 'update'])->middleware('guest')->name('change.perform');
	Route::get('/dashboard', [HomeController::class, 'index'])->name('home')->middleware('auth');
Route::group(['middleware' => 'auth'], function () {
	Route::get('/virtual-reality', [PageController::class, 'vr'])->name('virtual-reality');
	Route::get('/rtl', [PageController::class, 'rtl'])->name('rtl');
	Route::get('/profile', [UserProfileController::class, 'show'])->name('profile');
	Route::post('/profile', [UserProfileController::class, 'update'])->name('profile.update');
	Route::get('/profile-static', [PageController::class, 'profile'])->name('profile-static'); 
	Route::get('/sign-in-static', [PageController::class, 'signin'])->name('sign-in-static');
	Route::get('/sign-up-static', [PageController::class, 'signup'])->name('sign-up-static'); 
	Route::get('/{page}', [PageController::class, 'index'])->name('page');
	Route::post('logout', [LoginController::class, 'logout'])->name('logout');


	
});


//School Controller
Route::get('/school/create', function () {
    return view('school.create');
});

Route::post('/school/store', [SchoolController::class, 'store'])->name('school.store');



//file upload route
Route::get('/fileupload/create', function () {
    return view('fileupload.create');
});

Route::post('/fileupload/store', [FileUploadController::class, 'store'])->name('fileupload.store');


//Challenge route
use App\Http\Controllers\ChallengeController;

Route::get('/challenges/create', [ChallengeController::class, 'create'])->name('challenges.create');
Route::post('/challenges', [ChallengeController::class, 'store'])->name('challenges.store');

//Question route
use App\Http\Controllers\QuestionController;

Route::get('/qn-upload', [QuestionController::class, 'create'])->name('questions.create');
Route::post('/qn-upload', [QuestionController::class, 'store'])->name('questions.store');



//Answer route
use App\Http\Controllers\AnswerController;

Route::get('/upload', [AnswerController::class, 'create'])->name('answers.create');
Route::post('/upload', [AnswerController::class, 'store'])->name('answers.store');



//Routes for analytics
use App\Http\Controllers\AnalyticsController;

Route::get('/analytics/most-correctly-answered', [AnalyticsController::class, 'mostCorrectlyAnswered'])->name('analytics.mostCorrectlyAnswered');
Route::get('/analytics/school-rankings', [AnalyticsController::class, 'schoolRankings'])->name('analytics.schoolRankings');
Route::get('/analytics/performance-over-years', [AnalyticsController::class, 'performanceOverYears'])->name('analytics.performanceOverYears');
Route::get('/analytics/question-repetition', [AnalyticsController::class, 'questionRepetition'])->name('analytics.questionRepetition');
Route::get('/analytics/worst-performing-schools', [AnalyticsController::class, 'worstPerformingSchools'])->name('analytics.worstPerformingSchools');
Route::get('/analytics/best-performing-schools', [AnalyticsController::class, 'bestPerformingSchools'])->name('analytics.bestPerformingSchools');
Route::get('/analytics/incomplete-challenges', [AnalyticsController::class, 'incompleteChallenges'])->name('analytics.incompleteChallenges');


