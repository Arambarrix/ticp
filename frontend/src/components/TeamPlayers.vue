<script setup>
  
import { createPopper } from "@popperjs/core";
import { ref, defineProps, computed } from "vue";
const props = defineProps({'lettre':String, 'joueurs':Array});
const colors =["bg-blue-900", "bg-green-900", "bg-purple-900","bg-dark-brown"]

const dropdownPopoverShow = ref(false);
const btnDropdownRef = ref(null);
const popoverDropdownRef = ref(null);


function toggleDropdown(){
    if(dropdownPopoverShow.value){
        dropdownPopoverShow.value = false;
    } else {
        dropdownPopoverShow.value= true;
       createPopper(btnDropdownRef.value, popoverDropdownRef.value, {
            placement: "bottom-start"
        });
    }
}
</script>
<template>
    <button class="text-2xl text-white rounded-full w-10 h-10 ease-linear transition-all duration-150 hover:shadow-lg outline-none focus:outline-none" :class="colors[Math.floor(Math.random() * 4)]" @click="toggleDropdown()" ref="btnDropdownRef">
        {{lettre}}
    </button>
    <div class="flex flex-wrap">
      <div class="w-full sm:w-6/12 md:w-4/12 px-2">
        <div class="relative inline-flex align-middle w-full">
          <div v-bind:class="{'hidden': !dropdownPopoverShow, 'block': dropdownPopoverShow}" class="bg-white text-base z-50 float-left py-2 list-none text-left rounded shadow-lg mt-1" style="min-width:12rem" ref="popoverDropdownRef">
            <span v-for="joueur in props.joueurs" class="text-sm py-2 px-4 font-normal block w-full whitespace-nowrap bg-transparent  text-slate-700">
              {{joueur.nom}}
            </span>
           
          </div>
        </div>
      </div>
    </div>
</template>
  
