<script setup>
    import MatchListTableVue from './tableaux/MatchList.vue';
    import TeamListTableVue from './tableaux/TeamList.vue';

    var equipeList =[
        {"rang":1, "nom":"parppp", "point":215, "matchJoue":2, "matchTotal":3},
        {"rang":2, "nom":"ddsparppp dxedsq dcs dsx zdsx sxw ds zsq", "point":100, "matchJoue":1, "matchTotal":3},
        {"rang":3, "nom":"fdefrcsezd", "point":0, "matchJoue":0, "matchTotal":3},
        {"rang":4, "nom":"fzfc dcxfzsc ezds", "point":215, "matchJoue":2, "matchTotal":3},
        {"rang":4, "nom":"fzfc dcxfzsc ezds", "point":215, "matchJoue":2, "matchTotal":3},
        {"rang":4, "nom":"fzfc dcxfzsc ezds", "point":215, "matchJoue":2, "matchTotal":3},
        {"rang":4, "nom":"fzfc dcxfzsc ezds", "point":215, "matchJoue":2, "matchTotal":3},
        {"rang":4, "nom":"fzfc dcxfzsc ezds", "point":215, "matchJoue":2, "matchTotal":3},
        {"rang":4, "nom":"fzfc dcxfzsc ezds", "point":215, "matchJoue":2, "matchTotal":3}

    ]

    var matchList =[
        {"id":1,"numero":"A1", "equipe1":"dzsfessdezf", "equipe2":"Tatat", "gagnant":"Tatat", "score1":2, "score2":1},
        {"id":1,"numero":"A1", "equipe1":"parppp", "equipe2":"Tatat", "gagnant":"Tatat"},
        {"id":1,"numero":"A1", "equipe1":"sxzdsx ", "equipe2":"dzsxcc edzsxw", "gagnant":"c xdcxvdcx fdcx dc", "score1":20, "score2":56},
        {"id":1,"numero":"A1", "equipe1":"parppp", "equipe2":"Tatat", "gagnant":"Tatat"},
        {"id":1,"numero":"A1", "equipe1":"parppp", "equipe2":"Tatat e dz dqq dq", "gagnant":"Tatat", "score1":45, "score2":20},
    ]
</script>
<template>
    <div class="my-8">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Finale</p>
        <div class="flex flex-col md:flex-row justify-between">
            <MatchListTableVue class="w-full md:w-1/2" :data="matchList"/>
            <TeamListTableVue class="w-full md:w-1/2" :data="equipeList"/>
        </div>
    </div>

    <div class="my-8">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Demi Finale</p>
        <div class="flex flex-col md:flex-row justify-between">
            <MatchListTableVue class="w-full md:w-1/2" :data="matchList"/>
            <TeamListTableVue class="w-full md:w-1/2" :data="equipeList"/>
        </div>
    </div>

    <div class="my-8">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Quart de Finale</p>
        <div class="flex flex-col md:flex-row justify-between">
            <MatchListTableVue class="w-full md:w-1/2" :data="matchList"/>
            <TeamListTableVue class="w-full md:w-1/2" :data="equipeList"/>
        </div>
    </div>

    <div class="my-8">
        <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Huitième de Finale</p>
        <div class="flex flex-col md:flex-row justify-between">
            <MatchListTableVue class="w-full md:w-1/2" :data="matchList"/>
            <TeamListTableVue class="w-full md:w-1/2" :data="equipeList"/>
        </div>
    </div>
</template>