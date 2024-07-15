<?php
// app/Imports/AnswersImport.php
namespace App\Imports;

use App\Models\Answer;
use Maatwebsite\Excel\Concerns\ToModel;

class AnswersImport implements ToModel
{
    public function model(array $row)
    {
        return new Answer([
            'answer_text' => $row[0],
            'marks' => (int) $row[1], // Cast marks to integer
        ]);
    }
}

