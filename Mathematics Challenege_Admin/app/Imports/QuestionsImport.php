<?php
// app/Imports/QuestionsImport.php
namespace App\Imports;

use App\Models\Question;
use Maatwebsite\Excel\Concerns\ToModel;

class QuestionsImport implements ToModel
{
    public function model(array $row)
    {
        return new Question([
            'question_text' => $row[0],
        ]);
    }
}
