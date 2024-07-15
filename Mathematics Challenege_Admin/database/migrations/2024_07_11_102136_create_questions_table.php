<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
// database/migrations/xxxx_xx_xx_xxxxxx_create_questions_table.php
public function up()
{
    Schema::create('questions', function (Blueprint $table) {
        $table->id();
        $table->string('question_text');
        $table->timestamps();
    });
}

public function down()
{
    Schema::dropIfExists('questions');
}

};
