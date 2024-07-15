<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;


class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->insert([
            'username' => 'khemi',
            'firstname' => 'okema',
            'lastname' => 'paul',
            'email' => 'paulokema342@gmail.com',
            'password' => bcrypt('secret')
        ]);
    }
}
