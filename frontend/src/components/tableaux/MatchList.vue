<script setup>
    import { ref } from 'vue'

    const props = defineProps({'data':Object});
    var is_modal_toggled = ref(false)

    function renseigner_score(){
        is_modal_toggled.value = !is_modal_toggled.value
        console.log(is_modal_toggled.value)
    }
</script>

<template>
    <div class="flex flex-col">
        <div class="overflow-x-auto sm:mx-0.5 lg:mx-0.5">
            <div class="py-2 inline-block min-w-full sm:px-2 lg:px-4">
                <div class="overflow-hidden">
                    <table class="w-full flex flex-col space-y-2 table-auto	">
                        <thead class="bg-green-700 text-white font-bold uppercase rounded-md">
                            <tr class="flex flex-row justify-between ">
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-1/6">
                                    N
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-2/6">
                                    Matchs
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 text-left w-2/6">
                                    Gagnant
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 text-left w-1/6">
                                    Score
                                </th>
                            </tr>
                        </thead>
                        <tbody class="flex flex-col space-y-2">

                            <tr v-for="match in data" class="flex flex-row justify-between bg-gray-100  transition duration-300 ease-in-out rounded-md hover:bg-gray-200">
                                <td class="w-1/6 px-6 py-3 whitespace-nowrap text-sm text-center font-medium text-gray-900">{{match.numero}}</td>
                                <td class="w-2/6 text-sm text-gray-900 text-center font-light px-6 py-3 whitespace-nowrap overflow-hidden hover:overflow-visible hover:w-auto hover:whitespace-normal">
                                    <div class="flex flex-row">
                                        <span class="rounded-full px-3">{{match.equipe1}}</span>
                                        <span class="rounded-full px-3">{{match.equipe2}}</span>
                                    </div>
                                </td>
                                <td class="w-2/6 text-sm text-gray-900 text-center font-light px-6 py-3 whitespace-nowrap overflow-hidden hover:overflow-visible hover:w-auto hover:whitespace-normal">
                                    <span>{{match.gagnant}}</span>
                                </td>
                                <td class="w-1/6 text-sm text-white font-light px-6 py-3 whitespace-nowrap text-center">
                                    <span v-if="match.score" class="text-gray-900 px-3">{{match.score}}</span>
                                    <div v-else class="">
                                        <i class="fa-thin fa-pen text-dark-brown cursor-pointer font-bold" @click="renseigner_score()" title="Renseigner Score"></i>
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