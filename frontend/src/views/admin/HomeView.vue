<script setup>
    import InfoCardVue from '../../components/InfoCard.vue';
    import HistoriqueYearsVue from '../../components/historique/HistoriqueYears.vue';
    import SearchBar from '../../components/admin/SearchBar.vue';
    import MatchListTableVue from '../../components/admin/MatchList.vue';
    import { Tournois } from "@/stores/user/tournoi"
    import { Tableaux } from '../../stores/user/tableau';
    import { Poules } from '../../stores/user/poule';
    import { useRoute, useRouter, RouterLink } from "vue-router";
    import { watch, computed } from 'vue'

    const tournoiStore = Tournois();
    const tableauStore = Tableaux();
    const pouleStore = Poules();

    const route = useRoute();
    const router = useRouter()
    
    const year = computed(()=>{
        return route.params.year
    } )

    watch(year, yearChanged)

    const is_this_year_actif= computed(() =>{
        if (year.value == new Date().getFullYear()) return true;
    })

    tournoiStore.getTournoiInfo(year.value)
    tournoiStore.getAll()
    const tournois = computed(()=>tournoiStore.getTournois );

    const tournoi_tableaux_length = computed(()=>tournoiStore.getTableauxLength);
    const tournoi_equipes_length = computed(()=>tournoiStore.getEquipesLength);
    const tournoi_poules_length = computed(()=>tournoiStore.getPoulesLength);

    var infoCardDatas= computed(()=>{
        return {
            "equipe":{'image':'user.png', 'number': tournoi_equipes_length.value, 'text':"Equipes Inscrites", 'color':"#1B2A5A"},
            "poule":{'image':'group.png', 'number':tournoi_poules_length.value, 'text':"Poules Générées", 'color':"#195937"},
            "tableau":{'image':'network.png', 'number':tournoi_tableaux_length.value, 'text':"Tableaux crées", 'color':"#00253A"}
        } 
    }) 


    var historique_links= computed(() => {
    
        return {
            'tournois':tournois.value,
            'year_actif':year.value, 
            'base_link':"/admin/accueil/", 
        };

    });
    function generateTableau(){
        tableauStore.launch_creation();  
    }

    function generatePoule(){
        pouleStore.launch_creation();
    }

    

    function yearChanged(year){
        tournoiStore.getTournoiInfo(year)
    }
</script>

<template>
    <main>

        <div class="flex flex-col space-y-10 w-full ">
            
            <HistoriqueYearsVue  v-bind="historique_links" class="w-full px-6 md:px-8"/>

            <div class="grid grid-cols-2 md:grid-cols-3 gap-y-8 gap-x-16 justify-items-stretch mb-6">
                <InfoCardVue v-bind="infoCardDatas.equipe"/>
                <InfoCardVue v-bind="infoCardDatas.poule"/>
                <InfoCardVue v-bind="infoCardDatas.tableau"/>
            </div>

            <div class="w-full">
                <p class="my-5 text-dark-brown text-xl md:text-2xl font-bold">Tournois</p>
                <div class="justify-between h-auto grid grid-cols-2  md:grid-cols-5 gap-4 items-end">
                    <div class="text-center" >
                        <div v-if="is_this_year_actif" class="cursor-pointer text-white bg-blue-900 px-6 py-3 text-white font-bold" @click="generatePoule()">
                            <i class="fa-light fa-people-group" title="GenererPoules"></i>
                            <span>  Générer Poules</span>
                        </div>
                    </div>
                    <div class="text-center cursor-pointer">
                        <div v-if="is_this_year_actif" class="text-white bg-blue-900 px-6 py-3 text-white font-bold" @click="generateTableau()">
                            <i class="fa-regular fa-square-poll-vertical" title="GenererPoules"></i>
                            <span>  Générer Tableaux</span>
                        </div>
                    </div>
                    <div></div>
                    <SearchBar class="col-span-2 col-end" />
                </div>
            </div>

            <MatchListTableVue class="w-full" :data="tournois" />

        </div>
    </main>
</template>