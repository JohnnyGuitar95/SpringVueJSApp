<template>
  <main>
    <Navbar />
    <div class="my-5">
      <div class="mx-auto w-25 " style="max-width:100%;">
        <h2 class="text-center mb-3">Добавить профессию</h2>
        <form @submit.prevent="addProfession">
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="name" class="form-label">Название</label>
              <input id="name"  type="text" name="name" class="form-control" placeholder="Название" required v-model="profession.name">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="note" class="form-label">Примечание</label>
              <input id="note" type="text"  name="note" class="form-control" placeholder="Примечание" required v-model="profession.note" >
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Submit">
            </div>
          </div>
          <div>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>


<script>
import Navbar from '../../components/Navbar.vue';

export default {
  name: 'AddProfession',
  components: {
    Navbar
  },

  data() {
    return {
      profession : {
        name: '',
        note: ''
      }
    }
  },

  methods: {
    addProfession(){
      fetch('http://localhost:7000/professionsApi/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.profession)
      })
          .then(data => {
            console.log(data)
            this.$router.push("/professionsHome");
          })

    }
  },

}

</script>