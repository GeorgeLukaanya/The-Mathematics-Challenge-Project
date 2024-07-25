<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('results', function (Blueprint $table) {
            $table->id(); // Primary key
            $table->string('participant'); // Name or ID of the participant
            $table->string('school'); // School of the participant
            $table->string('challenge'); // Challenge name or ID
            $table->string('time_taken'); // Time taken for the challenge (consider changing to integer for seconds or float for minutes)
            $table->string('questions');
            $table->string('answers');
            $table->integer('Total_score'); // total marks obtained
            $table->timestamps(); // Created at and updated at timestamps
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('results');
    }
};