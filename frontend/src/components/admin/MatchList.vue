<script setup>
    import ModifierTournoiModal from './ModifierTournoiModal.vue';
    import { ref } from 'vue'
    const props = defineProps({'data':Object});


</script>

<template>
    <div class="flex flex-col">
        <div class="overflow-x-auto ">
            <div class="py-2 inline-block min-w-full ">
                <div class="overflow-hidden">
                    <table class="w-full flex flex-col table-auto	">
                        <thead class="bg-green-700 text-white font-bold lowercase rounded-md">
                            <tr class="flex flex-row justify-between ">
                                <th scope="col" class="text-sm text-center px-6 py-3 w-24">
                                    #
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 w-24">
                                    Année
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 w-24">
                                    DEBUT TOURNOI
                                </th>
                                <th scope="col" class="text-sm text-center px-6 py-3 w-24">
                                    Fin inscription
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 w-24">
                                    DEBUT POULE
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 w-24">
                                    FIN POULE
                                </th>
                                <th scope="col" class="text-sm  text-center px-6 py-3 w-24">
                                    DEBUT TABLEAU
                                </th>
                                <th scope="col" class="text-sm  text-center  px-6 py-3 w-24">
                                    FIN TOURNOI
                                </th>
                                <th scope="col" class="text-sm  text-center  px-6 py-3 w-24">
                                    Actions
                                </th>
                            </tr>
                        </thead>
                        <tbody class="flex flex-col space-y-2">
                            <tr v-for="(tournoi, i) in data" class="flex flex-row justify-between bg-gray-100  transition duration-300 ease-in-out rounded-md hover:bg-gray-200 whitespace-nowrap  overflow-hidden hover:overflow-visible hover:w-auto hover:whitespace-normal">
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">{{i+1}}</td>
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">{{tournoi.id}}</td>
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">{{tournoi.dateDebutTournoi}}</td>
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">{{tournoi.dateFinInscription}}</td>
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">{{tournoi.dateDebutPoule}}</td>
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">{{tournoi.dateFinPoule}}</td>
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">{{tournoi.dateDebutTableau}}</td>
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">{{tournoi.dateFinTournoi}}</td>
                                <td class="w-24 px-6 py-3  text-sm text-center text-gray-900">
                                    <ModifierTournoiModal :tournoi="tournoi" v-if="tournoi.id >= new Date().getFullYear()"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>