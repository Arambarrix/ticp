<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import BannerVue from "../../components/admin/banner.vue";
    import SearchBar from '../../components/admin/SearchBar.vue';
    import MatchListTableVue from '../../components/admin/MatchList.vue';
    import { Tournois } from "@/stores/user/tournoi"
    import { Tableaux } from '../../stores/user/tableau';
    import { Poules } from '../../stores/user/poule';
    import { useRoute, useRouter, RouterLink } from "vue-router";
    import { ref, computed } from 'vue'
    const tournoiStore = Tournois();
    const tableauStore = Tableaux();
    const pouleStore = Poules();

    const route = useRoute();
    const router = useRouter()
    const year = computed(() => {
        return route.params.rang
    })

    tournoiStore.getTournoiInfo()
    var actif = "tournoisHistorique";
    var infoCardDatas = {
        "joeur": { 'image': 'group.png', 'number': "289", 'text': "Nombre de joeurs", 'color': "#1B2A5A" },
        "equipe": { 'image': 'user.png', 'number': "55", 'text': "Equipes Inscrites", 'color': "#3A0000" },
        "match": { 'image': 'flag.png', 'number': "1452", 'text': "Matchs Termin�s", 'color': "#195937" }
    };

    var matchList = [
        { "id": 1, "date": "12/05/22", "valeur": 236, "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 2, "date": "12/05/22", "valeur": 236, "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 3, "date": "12/05/22", "valeur": 236, "equipe2": "dzsxcc edzsxw", "gagnant": "c xdcxvdcx fdcx dc", "score1": 20, "score2": 56 },
        { "id": 4, "date": "12/05/22", "valeur": 236, "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 5, "date": "12/05/22", "valeur": 236, "equipe2": "Tatat e dz dqq dq", "gagnant": "Tatat", "score1": 45, "score2": 20 },
        { "id": 1, "date": "12/05/22", "valeur": 236, "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 2, "date": "12/05/22", "valeur": 236, "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 3, "date": "12/05/22", "valeur": 236, "equipe2": "dzsxcc edzsxw", "gagnant": "c xdcxvdcx fdcx dc", "score1": 20, "score2": 56 },
        { "id": 4, "date": "12/05/22", "valeur": 236, "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 5, "date": "12/05/22", "valeur": 236, "equipe2": "Tatat e dz dqq dq", "gagnant": "Tatat", "score1": 45, "score2": 20 },
    ]

    function generateTableau(){
        tableauStore.success=""
        tableauStore.launch_creation();  
    }

    function generatePoule(){
        pouleStore.success=""
        pouleStore.launch_creation();
    }


</script>

<template>
    <main>

        <div class="py-10 snap-x">
            <div class="gap-y-32">
                <BannerVue :year="year" base_link="/admin/" :actif="actif" />
                <p></p>
            </div>
            <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-6">
                <InfoCardVue v-bind="infoCardDatas.joeur" />
                <InfoCardVue v-bind="infoCardDatas.equipe" />
                <InfoCardVue v-bind="infoCardDatas.match" />
            </div>

            <div class="w-96 ">

            </div>


            <div class="max-w-5xl ">
                <p class="my-5 text-dark-brown text-xl md:text-2xl font-bold">Tournois</p>
                <div class="justify-between h-12 grid grid-cols-5 gap-4">
                    <div class="text-center">
                        <div to="" class="cursor-pointer text-white bg-blue-900 px-6 py-3 text-white font-bold" @click="generatePoule()">
                            <i class="fa-light fa-people-group" title="GenererPoules"></i>
                            <span>  Générer Poules</span>
                        </div>
                    </div>
                    <div class="text-center cursor-pointer">
                        <div to="" class="text-white bg-blue-900 px-6 py-3 text-white font-bold" @click="generateTableau()">
                            <i class="fa-regular fa-square-poll-vertical" title="GenererPoules"></i>
                            <span>  Générer Tableaux</span>
                        </div>
                    </div>
                    <div></div>
                    <SearchBar class="col-span-2 col-end" />
                </div>
            </div>

            <div class="max-w-5xl object-left m-0">
                <MatchListTableVue class="" :data="matchList" />
            </div>

        </div>
    </main>
</template>