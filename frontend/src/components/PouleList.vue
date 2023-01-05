<script setup>
    import InfoCardVue from './InfoCard.vue';
    import MatchListTableVue from './tableaux/MatchList.vue';
    import TeamListTableVue from './tableaux/TeamList.vue';
    import PaginationVue from './Pagination.vue';

    var infoCardDatas={
        "equipe":{'image':'user.png', 'number':"89", 'text':"Equipes Inscrites", 'color':"#1B2A5A"},
        "match":{'image':'flag.png', 'number':"15", 'text':"Matchs Joués", 'color':"#3A0000"},
        "poule":{'image':'group.png', 'number':"12", 'text':"Poules Générées", 'color':"#195937"},
        "tableau":{'image':'network.png', 'number':"3", 'text':"Tableaux crées", 'color':"#00253A"}
    }

    var equipeList =[
        {"rang":1, "nom":"parppp", "point":215, "matchJoue":2, "matchTotal":3},
        {"rang":2, "nom":"ddsparppp dxedsq dcs dsx zdsx sxw ds zsq", "point":100, "matchJoue":1, "matchTotal":3},
        {"rang":3, "nom":"fdefrcsezd", "point":0, "matchJoue":0, "matchTotal":3},
        {"rang":4, "nom":"fzfc dcxfzsc ezds", "point":215, "matchJoue":2, "matchTotal":3}
    ]

    var matchList =[
        {"id":1,"numero":"A1", "equipe1":"dzsfessdezf", "equipe2":"Tatat", "gagnant":"Tatat", "score1":2, "score2":1},
        {"id":1,"numero":"A1", "equipe1":"parppp", "equipe2":"Tatat", "gagnant":""},
        {"id":1,"numero":"A1", "equipe1":"sxzdsx ", "equipe2":"dzsxcc edzsx ", "gagnant":""},
        {"id":1,"numero":"A1", "equipe1":"parppp", "equipe2":"Tatat", "gagnant":""},
        {"id":1,"numero":"A1", "equipe1":"parppp", "equipe2":"Tatat e dz dqq dq", "gagnant":"Tatat s s s zasq zsq ", "score1":45, "score2":20},
    ]
</script>
<template>
    <div class="py-10 ">

        <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-8">
            <InfoCardVue v-bind="infoCardDatas.equipe"/>
            <InfoCardVue v-bind="infoCardDatas.match"/>
            <InfoCardVue v-bind="infoCardDatas.poule"/>
            <InfoCardVue v-bind="infoCardDatas.tableau"/>
        </div>

        <div class="my-8">
            <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Poule A</p>
            <div class="flex flex-col md:flex-row justify-between">
                <MatchListTableVue class="w-full md:w-1/2" :data="matchList"/>
                <TeamListTableVue class="w-full md:w-1/2" :data="equipeList"/>
            </div>
        </div>

        <div class="my-8">
            <p class="my-5 text-dark-brown text-lg md:text-2xl text-center font-bold">Poule B</p>
            <div class="flex flex-col md:flex-row justify-between">
                <MatchListTableVue class="w-full md:w-1/2" :data="matchList"/>
                <TeamListTableVue class="w-full md:w-1/2" :data="equipeList"/>
            </div>
        </div>

        <PaginationVue/>

    </div>
   
</template>