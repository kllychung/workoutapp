package com.example.workoutapp

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(1, "jumping jacks", R.drawable.ic_jumping_jacks, false, false)
        exerciseList.add(jumpingJacks)

        val plank = ExerciseModel(2, "plank", R.drawable.ic_plank, false, false)
        exerciseList.add(plank)

        val pushUp = ExerciseModel(3, "push up", R.drawable.ic_push_up, false, false)
        exerciseList.add(pushUp)

        val squat = ExerciseModel(4, "squat", R.drawable.ic_squat, false, false)
        exerciseList.add(squat)

        val stepUpOntoChair = ExerciseModel(5, "step up onto chair", R.drawable.ic_step_up_onto_chair, false, false)
        exerciseList.add(stepUpOntoChair)

        val tricepsDipOnChair = ExerciseModel(6, "triceps dip on chair", R.drawable.ic_triceps_dip_on_chair, false, false)
        exerciseList.add(tricepsDipOnChair)

        val wallSit = ExerciseModel(7, "wall sit", R.drawable.ic_wall_sit, false, false)
        exerciseList.add(wallSit)

        return exerciseList;
    }
}