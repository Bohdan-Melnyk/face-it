I use Scheduler to update all jobs in database.
Data is updated every 2 minutes.
You can change it by modifying next cron expression "0 0/2 * * * ?".
This cron in the JobScheduler class in package com.example.faceit.schedule.
Project is deployed in Heroku: https://face-it-544355e3224f.herokuapp.com/api/jobs