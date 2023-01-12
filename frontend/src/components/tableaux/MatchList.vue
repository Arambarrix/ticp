<script setup>
    import RenseignerScoreModalVue from './RenseignerScoreModal.vue';
    import { Tournois } from "@/stores/user/tournoi"
    import { computed } from 'vue'

    const props = defineProps({'data':Object, 'type':String, 'can_edit':Boolean, 'rang':String});
    const tournoiStore = Tournois();
    tournoiStore.getTournoiInfo()

    const  is_poule_phase_on = computed(()=>{
        const date = new Date().setHours(0,0,0,0);
        const dateDebutPoule = new Date(tournoiStore.current_tournoi.dateDebutPoule).setHours(0,0,0,0);
        const dateFinPoule = new Date(tournoiStore.current_tournoi.dateFinPoule).setHours(0,0,0,0);

        if( date < dateDebutPoule || date > dateFinPoule){
            return false
        }
        return true
    })

    const  is_tableau_phase_on = computed(()=>{
        const date = new Date().setHours(0,0,0,0);
        const dateDebutTableau = new Date(tournoiStore.current_tournoi.dateDebutTableau).setHours(0,0,0,0);
        const dateFinTournoi = new Date(tournoiStore.current_tournoi.dateFinTournoi).setHours(0,0,0,0);


        if( date >= dateDebutTableau && date <= dateFinTournoi){
            return true
        }
        return false
    })


</script>

<template>
    <div class="flex flex-col">
        <div class="overflow-x-auto sm:mx-0.5 lg:mx-0.5">
            <div class="py-2 inline-block min-w-full sm:px-2 lg:px-4">
                <div class="overflow-hidden">
                    <table class="w-full flex flex-col table-auto	">
                        <thead class="bg-green-700 text-white font-bold uppercase rounded-md">
                            <tr class="flex flex-row justify-between ">
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-1/5">
                                    N
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-2/5">
                                    Matchs
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-1/5">
                                    Gagnant
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 text-left w-1/5">
                                    Score
                                </th>
                            </tr>
                        </thead>
                        <tbody class="flex flex-col space-y-2">

                            <tr v-for="(match,i) in data" class="flex flex-row justify-between bg-gray-100  transition duration-300 ease-in-out rounded-md hover:bg-gray-200 whitespace-nowrap  overflow-hidden hover:overflow-visible hover:w-auto hover:whitespace-normal">
                                
                                <td v-if="match.equipeA && match.equipeB" class="w-1/5 px-6 py-3  text-sm text-center font-medium text-gray-900">{{ i+1 }}</td>
                                <td v-if="match.equipeA && match.equipeB" class="w-2/5 text-sm text-gray-900 text-center font-light px-6 py-3 overflow-hidden ">
                                    <div  class="flex flex-row justify-between">
                                        <span class="rounded-full px-3 ">{{match.equipeA.nom}}</span>
                                        <span class="rounded-full px-3">{{match.equipeB.nom}}</span>
                                    </div>
                                </td>
                                <td v-if="match.equipeA && match.equipeB" class="w-1/5 text-sm text-gray-900 text-center font-light px-6 py-3  overflow-hidden">
                                    <span v-if="match.scoreA == match.scoreB" >Aucun</span>
                                    <span v-else>{{match.scoreA > match.scoreB ?  match.equipeA.nom:match.equipeB.nom}}</span>
                                </td>
                                <td v-if="match.equipeA && match.equipeB" class="w-1/5 text-sm text-white font-light px-6 py-3 text-center overflow-hidden">
                                    <div v-if="can_edit && ((type=='poule' && is_poule_phase_on) || (type=='tableau' && is_tableau_phase_on))"  class="flex flex-row justify-between">
                                        <span class="text-gray-900 px-3">{{match.scoreA + " - " + match.scoreB}}</span>
                                        <div >
                                            <RenseignerScoreModalVue  :match="match" :type="type" :rang="rang"/>
                                        </div>
                                    </div>

                                    <span v-else class="text-gray-900 px-3">{{match.scoreA + " - " + match.scoreB}}</span>

                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>