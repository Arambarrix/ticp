<script setup>
    import RenseignerScoreModalVue from './RenseignerScoreModal.vue';
    import { Tournois } from "@/stores/user/tournoi"
    import { computed } from 'vue'

    const props = defineProps({'data':Object, 'type':String, 'can_edit':Boolean, 'rang':String});
    const tournoiStore = Tournois();

    const  is_poule_phase_on = computed(()=>{
        
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
                                    <div  class="flex flex-row">
                                        <span class="rounded-full px-3 ">{{match.equipeA.nom}}</span>
                                        <span class="rounded-full px-3">{{match.equipeB.nom}}</span>
                                    </div>
                                </td>
                                <td v-if="match.equipeA && match.equipeB" class="w-1/5 text-sm text-gray-900 text-center font-light px-6 py-3  overflow-hidden">
                                    <span v-if="match.scoreA && match.scoreB">{{match.scoreA > match.scoreB ?  match.equipeA.nom:match.equipeB.nom}}</span>
                                </td>
                                <td v-if="match.equipeA && match.equipeB" class="w-1/5 text-sm text-white font-light px-6 py-3 text-center">
                                    <div v-if="(!match.scoreA || !match.scoreB) && can_edit">
                                        <RenseignerScoreModalVue  :match="match" :type="type" :rang="rang"/>
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