<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import BannerButtonListVue from '../../components/admin/ButtonList.vue';
    import SearchBar from '../../components/admin/SearchBar.vue';
    import BoutonGenerate from '../../components/admin/ButtonGenerate.vue';
    import MatchListTableVue from '../../components/admin/MatchList.vue';
    import BannerVue from "../../components/admin/banner.vue";
    import { Tournois } from "@/stores/user/tournoi"
    import { useRoute, useRouter, RouterLink } from "vue-router";
    import { ref, computed } from 'vue'
import { Tableaux } from '../../stores/user/tableau';
    const tournoiStore = Tournois();
    const tableauStore= Tableaux();

    const route = useRoute();
    const router = useRouter()
    const year = computed(() => {
        return route.params.rang
    })

    tournoiStore.getTournoiInfo()
    var actif = "Tableaux";

    var infoCardDatas = {
        "joeur": { 'image': 'group.png', 'number': "289", 'text': "Nombre de joeurs", 'color': "#1B2A5A" },
        "equipe": { 'image': 'user.png', 'number': "55", 'text': "Equipes Inscrites", 'color': "#3A0000" },
        "match": { 'image': 'flag.png', 'number': "1452", 'text': "Matchs Termin�s", 'color': "#195937" }
    };

    var banner_data = computed(() => {
        return {
            'list_text_links': [{ 'text': 'Poules', 'link': "/admin/poules/" + year.value }, {
                'text': 'Tableaux', 'link': "/admin/tableaux/" + year.value
            }, {
                'text': 'Equipes', 'link': "/admin/equipe/" + year.value
            }], 'actif': "Tableaux", 'is_fully_rounded': true
        }
    });

    var poule_button = computed(() => {
        return {
            'button_info': { 'link': "/admin/poules/", 'text': "Poules" }, 'actif': "Poules", 'is_fully_rounded': true
        }
    });

    var matchList = [
        { "id": 1, "numero": "A1", "equipe1": "dzsfessdezf", "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 2, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 3, "numero": "A1", "equipe1": "sxzdsx ", "equipe2": "dzsxcc edzsxw", "gagnant": "c xdcxvdcx fdcx dc", "score1": 20, "score2": 56 },
        { "id": 4, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 5, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat e dz dqq dq", "gagnant": "Tatat", "score1": 45, "score2": 20 },
        { "id": 1, "numero": "A1", "equipe1": "dzsfessdezf", "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 2, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 3, "numero": "A1", "equipe1": "sxzdsx ", "equipe2": "dzsxcc edzsxw", "gagnant": "c xdcxvdcx fdcx dc", "score1": 20, "score2": 56 },
        { "id": 4, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat", "gagnant": "Tatat", "score1": 2, "score2": 1 },
        { "id": 5, "numero": "A1", "equipe1": "parppp", "equipe2": "Tatat e dz dqq dq", "gagnant": "Tatat", "score1": 45, "score2": 20 },
    ]

    const success = computed(() => { 
        return tableauStore.success; 
    })

    const errors = computed(() => { 
        return tableauStore.errors; 
    })

    function generateTableau(){
        console.log("chhcg")
        tableauStore.success=""
        tableauStore.launch_creation();
        
    }

</script>

<template>
    <main>

        <div class="py-10 ">
            <div class="gap-y-32">
                <BannerVue :year="year" base_link="/admin/tableaux/" :actif="actif" />
                <p></p>
            </div>
            <div class="grid grid-cols-2 md:grid-cols-4 gap-y-8 gap-x-16 justify-items-stretch mb-16">
                <InfoCardVue v-bind="infoCardDatas.joeur" />
                <InfoCardVue v-bind="infoCardDatas.equipe" />
                <InfoCardVue v-bind="infoCardDatas.match" />
            </div>

            <div class="w-96 ">
                <BannerButtonListVue v-bind="banner_data" />
            </div>


            <div class="max-w-5xl justify-items-stretch ">
                <p class="my-5 text-dark-brown text-xl md:text-2xl font-bold">Tableaux</p>
                <div class="justify-between grid grid-cols-3 grid-flow-col gap-4">

                    <BoutonGenerate v-bind="poule_button" class="text-white bg-green-700 rounded-full" @click="generateTableau()"/>
                    <div></div>
                    <SearchBar class="col-span-1 col-end" />

                </div>
            </div>

            <div class="max-w-5xl object-left m-0">
                <MatchListTableVue class="" :data="matchList" />
            </div>

        </div>
    </main>
</template>