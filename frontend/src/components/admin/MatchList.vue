<script setup>
    import { ref } from 'vue'
    import RenseignerScoreModalVue from '../tableaux/RenseignerScoreModal.vue';

    const props = defineProps({'data':Object});
   

</script>

<template>
    <div class="flex flex-col">
        <div class="overflow-x-auto ">
            <div class="py-2 inline-block min-w-full ">
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

                            <tr v-for="match in data" class="flex flex-row justify-between bg-gray-100  transition duration-300 ease-in-out rounded-md hover:bg-gray-200 whitespace-nowrap  overflow-hidden hover:overflow-visible hover:w-auto hover:whitespace-normal">
                                <td class="w-1/5 px-6 py-3  text-sm text-center font-medium text-gray-900">{{match.numero}}</td>
                                <td class="w-2/5 text-sm text-gray-900 text-center font-light px-6 py-3 overflow-hidden ">
                                    <div class="flex flex-row">
                                        <span class="rounded-full px-3 ">{{match.equipe1}}</span>
                                        <span class="rounded-full px-3">{{match.equipe2}}</span>
                                    </div>
                                </td>
                                <td class="w-1/5 text-sm text-gray-900 text-center font-light px-6 py-3  overflow-hidden">
                                    <span>{{match.gagnant}}</span>
                                </td>
                                <td class="w-1/5 text-sm text-white font-light px-6 py-3 text-center">
                                    <span v-if="match.score1 && match.score2" class="text-gray-900 px-3">{{match.score1 + " - " + match.score2}}</span>
                                    <div v-else>
                                        <RenseignerScoreModalVue  :match="match"/>
                                    </div>

                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>