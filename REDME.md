I use Scheduler to update all jobs i database.
Data is updated every 2 minutes.
You can change it by modifying next cron expression "0 0/2 * * * ?".
This cron in the JobScheduler class in package com.example.faceit.schedule.